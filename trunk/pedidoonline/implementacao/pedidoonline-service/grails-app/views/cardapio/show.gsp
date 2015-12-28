
<%@ page import="pedidoonline.service.Cardapio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cardapio.label', default: 'Cardapio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cardapio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cardapio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cardapio">
			
				<g:if test="${cardapioInstance?.categorias}">
				<li class="fieldcontain">
					<span id="categorias-label" class="property-label"><g:message code="cardapio.categorias.label" default="Categorias" /></span>
					
						<g:each in="${cardapioInstance.categorias}" var="c">
						<span class="property-value" aria-labelledby="categorias-label"><g:link controller="categoria" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cardapioInstance?.nomeEstabelecimento}">
				<li class="fieldcontain">
					<span id="nomeEstabelecimento-label" class="property-label"><g:message code="cardapio.nomeEstabelecimento.label" default="Nome Estabelecimento" /></span>
					
						<span class="property-value" aria-labelledby="nomeEstabelecimento-label"><g:fieldValue bean="${cardapioInstance}" field="nomeEstabelecimento"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cardapioInstance?.id}" />
					<g:link class="edit" action="edit" id="${cardapioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
