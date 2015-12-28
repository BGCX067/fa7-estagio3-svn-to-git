package br.com.pedidoonline;

import grails.converters.JSON;
import groovy.lang.GroovyObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException;
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller;
import org.codehaus.groovy.grails.web.json.JSONWriter;
import org.springframework.beans.BeanUtils;

public class NoClassNameObjectMarshaller implements ObjectMarshaller<JSON> {

	
	private List<String> ignoredProperties;
	
	public void init() {
		if(ignoredProperties == null) {
			ignoredProperties = new ArrayList<String>();
			ignoredProperties.add("attached");
			ignoredProperties.add("class");//
			ignoredProperties.add("belongsTo");
			ignoredProperties.add("constraints");
			ignoredProperties.add("allErrors");
			ignoredProperties.add("errorCount");
			ignoredProperties.add("fieldError");
			ignoredProperties.add("fieldErrors");
			ignoredProperties.add("fieldErrorCount");
			ignoredProperties.add("globalError");
			ignoredProperties.add("fieldErrors");
			ignoredProperties.add("globalErrorCount");
			ignoredProperties.add("messageCodesResolver");
			ignoredProperties.add("metaClass");
			ignoredProperties.add("additionalMetaMethods");
			ignoredProperties.add("errors");
		}
	}
	
	public boolean supports(Object object) {
		return object instanceof GroovyObject;
	}
	

	public void marshalObject(Object o, JSON json) throws ConverterException {
		JSONWriter writer = json.getWriter();
		init();
		try {
			writer.object();
			for (PropertyDescriptor property : BeanUtils.getPropertyDescriptors(o.getClass())) {
				String name = property.getName();
				Method readMethod = property.getReadMethod();
				if (readMethod != null && !ignoredProperties.contains(name)) {
					Object value = readMethod.invoke(o, (Object[]) null);
					writer.key(name);
					json.convertAnother(value);
				}
			}
			for (Field field : o.getClass().getDeclaredFields()) {
				int modifiers = field.getModifiers();
				if (Modifier.isPublic(modifiers) && !(Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))) {
					writer.key(field.getName());
					json.convertAnother(field.get(o));
				}
			}
			writer.endObject();
		} catch (ConverterException ce) {
			throw ce;
		} catch (Exception e) {
			throw new ConverterException("Error converting Bean with class " + o.getClass().getName(), e);
		}
	}
}
