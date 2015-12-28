<%@ page import="pedidoonline.service.Fatura" %>



<div class="fieldcontain ${hasErrors(bean: faturaInstance, field: 'conta', 'error')} required">
	<label for="conta">
		<g:message code="fatura.conta.label" default="Conta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="conta" name="conta.id" from="${pedidoonline.service.Conta.list()}" optionKey="id" required="" value="${faturaInstance?.conta?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: faturaInstance, field: 'valorParcial', 'error')} required">
	<label for="valorParcial">
		<g:message code="fatura.valorParcial.label" default="Valor Parcial" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="valorParcial" value="${fieldValue(bean: faturaInstance, field: 'valorParcial')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: faturaInstance, field: 'valorTotal', 'error')} required">
	<label for="valorTotal">
		<g:message code="fatura.valorTotal.label" default="Valor Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="valorTotal" value="${fieldValue(bean: faturaInstance, field: 'valorTotal')}" required=""/>
</div>

