package com.miles.seed.base.init;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomMapEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class BindingInitializer implements WebBindingInitializer {  
  
    public void initBinder(WebDataBinder binder, WebRequest request) {  

        binder.registerCustomEditor(Date.class,   
            new CustomDateEditor());  
        
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        
		//register spring default custom editor
		binder.registerCustomEditor(Collection.class, new CustomCollectionEditor(Collection.class));
		binder.registerCustomEditor(Set.class, new CustomCollectionEditor(Set.class));
		binder.registerCustomEditor(SortedSet.class, new CustomCollectionEditor(SortedSet.class));
		binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class));
		binder.registerCustomEditor(Map.class, new CustomMapEditor(Map.class));
		binder.registerCustomEditor(SortedMap.class, new CustomMapEditor(SortedMap.class));
		binder.registerCustomEditor(boolean.class, new CustomBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
		binder.registerCustomEditor(byte.class, new CustomNumberEditor(Byte.class, false));
		binder.registerCustomEditor(Byte.class, new CustomNumberEditor(Byte.class, true));
		binder.registerCustomEditor(short.class, new CustomNumberEditor(Short.class, false));
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(Short.class, true));
		binder.registerCustomEditor(int.class, new CustomNumberEditor(Integer.class, false));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(long.class, new CustomNumberEditor(Long.class, false));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(float.class, new CustomNumberEditor(Float.class, false));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(double.class, new CustomNumberEditor(Double.class, false));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
        
    }  
  
} 