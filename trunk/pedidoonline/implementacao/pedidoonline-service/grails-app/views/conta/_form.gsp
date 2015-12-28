<%@ page import="pedidoonline.service.Conta" %>



<div class="fieldcontain ${hasErrors(bean: contaInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="conta.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="codigo" type="number" value="${contaInstance.codigo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaInstance, field: 'fatura', 'error')} required">
	<label for="fatura">
		<g:message code="conta.fatura.label" default="Fatura" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fatura" name="fatura.id" from="${pedidoonline.service.Fatura.list()}" optionKey="id" required="" value="${contaInstance?.fatura?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaInstance, field: 'mesa', 'error')} required">
	<label for="mesa">
		<g:message code="conta.mesa.label" default="Mesa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mesa" name="mesa.id" from="${pedidoonline.service.Mesa.list()}" optionKey="id" required="" value="${contaInstance?.mesa?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaInstance, field: 'pedidos', 'error')} ">
	<label for="pedidos">
		<g:message code="conta.pedidos.label" default="Pedidos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${contaInstance?.pedidos?}" var="p">
    <li><g:link controller="pedido" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="pedido" action="create" params="['conta.id': contaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'pedido.label', default: 'Pedido')])}</g:link>
</li>
</ul>

</div>

