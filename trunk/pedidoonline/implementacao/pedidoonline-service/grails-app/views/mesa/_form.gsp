<%@ page import="pedidoonline.service.Mesa" %>



<div class="fieldcontain ${hasErrors(bean: mesaInstance, field: 'area', 'error')} required">
	<label for="area">
		<g:message code="mesa.area.label" default="Area" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="area" name="area.id" from="${pedidoonline.service.Area.list()}" optionKey="id" required="" value="${mesaInstance?.area?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mesaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="mesa.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" value="${mesaInstance.numero}" required=""/>
</div>

