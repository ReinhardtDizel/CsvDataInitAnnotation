package academy.reyngardt.annotation.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.ArrayList;

/**
 * @author Mikhail Reyngardt 23.04.2022
 */
public class ClassTypeManager {

    private final Map<UsedType, ClassName> classNameUtilMap;

    public ClassTypeManager() {
        classNameUtilMap = new HashMap<>();
    }

    public ClassName getContainerClassName() {
        return classNameUtilMap.get(UsedType.CONTAINER);
    }

    public ClassName getEntityClassName() {
        return classNameUtilMap.get(UsedType.ENTITY);
    }

    public TypeName getFieldTypeName() {
        return ParameterizedTypeName.get(getContainerClassName(), getEntityClassName());
    }

    public TypeName getCsvReaderTypeName() {
        return ParameterizedTypeName.get(ClassName.get(CsvDataReader.class), getEntityClassName());
    }

    public void setFiledType(TypeMirror typeMirror) {
        List<ClassName> nameList = null;
        String[] array = typeMirror.toString().split("<", 0);
        if (array.length > 1) {
            nameList = ClassTypeManagerUtils.getAnnotatedFiledClassName(array);
        }
        Objects.requireNonNull(nameList, "in ClassTypeManager setFiledType() nameList is NULL");
        ClassTypeManager.this.classNameUtilMap.put(UsedType.CONTAINER, nameList.get(0));
        ClassTypeManager.this.classNameUtilMap.put(UsedType.ENTITY, nameList.get(1));
    }

    public void setEnclosingType(TypeMirror typeMirror) {
        List<String> classNames = ClassTypeManagerUtils.getSplitsClassName(typeMirror.toString());
        Objects.requireNonNull(classNames, "in ClassTypeManager setEnclosingType() classNames is NULL");
        ClassName name = ClassName.get(classNames.get(0), classNames.get(1));
        ClassTypeManager.this.classNameUtilMap.put(UsedType.ENCLOSING_ELEMENT, name);
    }

    private enum UsedType {
        ENTITY,
        ENCLOSING_ELEMENT,
        CONTAINER
    }

    private static class ClassTypeManagerUtils {

        private static List<ClassName> getAnnotatedFiledClassName(String[] strings) {
            strings[1] = strings[1].replace(">", "");
            List<ClassName> res = new ArrayList<>();
            List<String> firstType = getSplitsClassName(strings[0]);
            List<String> secondType = getSplitsClassName(strings[1]);
            res.add(ClassName.get(firstType.get(0), firstType.get(1)));
            res.add(ClassName.get(secondType.get(0), secondType.get(1)));
            return res;
        }

        private static List<String> getSplitsClassName(String name) {
            String[] value = name.split("\\.");
            StringBuilder packageName = new StringBuilder();
            StringBuilder simpleName = new StringBuilder();
            List<String> res = new ArrayList<>();
            for (int i = 0; i < value.length - 1; i++) {
                packageName.append(value[i]);
                if (i < value.length - 2) {
                    packageName.append(".");
                }
            }
            simpleName.append(value[value.length - 1]);
            res.add(packageName.toString());
            res.add(simpleName.toString());
            return res;
        }
    }
}
