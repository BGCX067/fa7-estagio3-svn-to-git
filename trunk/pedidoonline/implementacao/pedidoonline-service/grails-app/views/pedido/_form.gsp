<%@ page import="pedidoonline.service.Pedido" %>



<div class="fieldcontain ${hasErrors(bean: pedidoInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="pedido.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="codigo" type="number" value="${pedidoInstance.codigo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pedidoInstance, field: 'itens', 'error')} ">
	<label for="itens">
		<g:message code="pedido.itens.label" default="Itens" />
		
	</label>
	<g:select name="itens" from="${pedidoonline.service.ItemPedido.list()}" multiple="multiple" optionKey="id" size="5" value="${pedidoInstance?.itens*.id}" class="many-to-many"/>
</div>

