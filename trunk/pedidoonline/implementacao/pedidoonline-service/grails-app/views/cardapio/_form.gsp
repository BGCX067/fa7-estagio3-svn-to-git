<%@ page import="pedidoonline.service.Cardapio" %>



<div class="fieldcontain ${hasErrors(bean: cardapioInstance, field: 'categorias', 'error')} ">
	<label for="categorias">
		<g:message code="cardapio.categorias.label" default="Categorias" />
		
	</label>
	<g:select name="categorias" from="${pedidoonline.service.Categoria.list()}" multiple="multiple" optionKey="id" size="5" value="${cardapioInstance?.categorias*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardapioInstance, field: 'nomeEstabelecimento', 'error')} ">
	<label for="nomeEstabelecimento">
		<g:message code="cardapio.nomeEstabelecimento.label" default="Nome Estabelecimento" />
		
	</label>
	<g:textField name="nomeEstabelecimento" value="${cardapioInstance?.nomeEstabelecimento}"/>
</div>

