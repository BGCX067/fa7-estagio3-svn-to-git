<%@ page import="pedidoonline.service.ItemPedido" %>



<div class="fieldcontain ${hasErrors(bean: itemPedidoInstance, field: 'item', 'error')} required">
	<label for="item">
		<g:message code="itemPedido.item.label" default="Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="item" name="item.id" from="${pedidoonline.service.Item.list()}" optionKey="id" required="" value="${itemPedidoInstance?.item?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemPedidoInstance, field: 'quantidade', 'error')} required">
	<label for="quantidade">
		<g:message code="itemPedido.quantidade.label" default="Quantidade" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="quantidade" type="number" value="${itemPedidoInstance.quantidade}" required=""/>
</div>

