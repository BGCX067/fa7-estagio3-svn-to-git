<%@ page import="pedidoonline.service.Area" %>



<div class="fieldcontain ${hasErrors(bean: areaInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="area.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="codigo" type="number" value="${areaInstance.codigo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: areaInstance, field: 'garcons', 'error')} ">
	<label for="garcons">
		<g:message code="area.garcons.label" default="Garcons" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${areaInstance?.garcons?}" var="g">
    <li><g:link controller="garcom" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="garcom" action="create" params="['area.id': areaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'garcom.label', default: 'Garcom')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: areaInstance, field: 'mesas', 'error')} ">
	<label for="mesas">
		<g:message code="area.mesas.label" default="Mesas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${areaInstance?.mesas?}" var="m">
    <li><g:link controller="mesa" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="mesa" action="create" params="['area.id': areaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'mesa.label', default: 'Mesa')])}</g:link>
</li>
</ul>

</div>

