
<%@ page import="pedidoonline.service.Fatura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fatura.label', default: 'Fatura')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fatura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fatura" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fatura">
			
				<g:if test="${faturaInstance?.conta}">
				<li class="fieldcontain">
					<span id="conta-label" class="property-label"><g:message code="fatura.conta.label" default="Conta" /></span>
					
						<span class="property-value" aria-labelledby="conta-label"><g:link controller="conta" action="show" id="${faturaInstance?.conta?.id}">${faturaInstance?.conta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${faturaInstance?.valorParcial}">
				<li class="fieldcontain">
					<span id="valorParcial-label" class="property-label"><g:message code="fatura.valorParcial.label" default="Valor Parcial" /></span>
					
						<span class="property-value" aria-labelledby="valorParcial-label"><g:fieldValue bean="${faturaInstance}" field="valorParcial"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${faturaInstance?.valorTotal}">
				<li class="fieldcontain">
					<span id="valorTotal-label" class="property-label"><g:message code="fatura.valorTotal.label" default="Valor Total" /></span>
					
						<span class="property-value" aria-labelledby="valorTotal-label"><g:fieldValue bean="${faturaInstance}" field="valorTotal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${faturaInstance?.id}" />
					<g:link class="edit" action="edit" id="${faturaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
