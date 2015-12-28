<%@ page import="pedidoonline.service.Garcom" %>



<div class="fieldcontain ${hasErrors(bean: garcomInstance, field: 'area', 'error')} required">
	<label for="area">
		<g:message code="garcom.area.label" default="Area" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="area" name="area.id" from="${pedidoonline.service.Area.list()}" optionKey="id" required="" value="${garcomInstance?.area?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: garcomInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="garcom.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${garcomInstance?.nome}"/>
</div>

