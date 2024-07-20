package np.com.prahladpanthi.seminaronebackend.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BeanCopyUtils {

    public static void copyNonNullProperties(Object source, Object target, String... ignoreProperties) {
        Set<String> ignorePropertiesSet = getNullPropertyNames(source);

        Collections.addAll(ignorePropertiesSet, ignoreProperties);
        BeanUtils.copyProperties(source, target, ignorePropertiesSet.toArray(new String[0]));
    }

    private static Set<String> getNullPropertyNames(Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> emptyProperties = new HashSet<>();
        for (PropertyDescriptor pd : propertyDescriptors) {
            Object src = beanWrapper.getPropertyValue(pd.getName());
            if (src == null) emptyProperties.add(pd.getName());
        }
        return emptyProperties;
    }
}
