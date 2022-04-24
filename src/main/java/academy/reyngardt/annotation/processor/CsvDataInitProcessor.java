package academy.reyngardt.annotation.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mikhail Reyngardt 23.04.2022
 */
@SupportedAnnotationTypes(
        "academy.reyngardt.annotation.processor.CsvDataInit")
@SupportedSourceVersion(SourceVersion.RELEASE_9)
@AutoService(Processor.class)
public class CsvDataInitProcessor extends AbstractProcessor {

    private final Map<TypeElement, CsvDataInitVisitor> mVisitors = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(CsvDataInit.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations.isEmpty()) {
            return false;
        }
        final Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(CsvDataInit.class);
        for (final Element element : elements) {
            final TypeElement object = (TypeElement) element.getEnclosingElement();
            CsvDataInitVisitor visitor = mVisitors.get(object);
            if (visitor == null) {
                visitor = new CsvDataInitVisitor(processingEnv, object);
                mVisitors.put(object, visitor);
            }
            element.accept(visitor, null);
        }

        for (final CsvDataInitVisitor visitor : mVisitors.values()) {
            visitor.brewJava();
        }

        return true;
    }
}
