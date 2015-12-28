<%@ page import="pedidoonline.service.Item" %>



<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="item.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="codigo" type="number" value="${itemInstance.codigo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="item.descricao.label" default="Descricao" />
		
	</label>
	<g:textField name="descricao" value="${itemInstance?.descricao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'imagem', 'error')} ">
	<label for="imagem">
		<g:message code="item.imagem.label" default="Imagem" />
		
	</label>
	<g:textField name="imagem" value="${itemInstance?.imagem}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="item.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${itemInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemInstance, field: 'valor', 'error')} required">
	<label for="valor">
		<g:message code="item.valor.label" default="Valor" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="valor" value="${fieldValue(bean: itemInstance, field: 'valor')}" required=""/>
</div>

