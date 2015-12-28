
<%@ page import="pedidoonline.service.Conta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'conta.label', default: 'Conta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-conta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-conta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list conta">
			
				<g:if test="${contaInstance?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="conta.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${contaInstance}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaInstance?.fatura}">
				<li class="fieldcontain">
					<span id="fatura-label" class="property-label"><g:message code="conta.fatura.label" default="Fatura" /></span>
					
						<span class="property-value" aria-labelledby="fatura-label"><g:link controller="fatura" action="show" id="${contaInstance?.fatura?.id}">${contaInstance?.fatura?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaInstance?.mesa}">
				<li class="fieldcontain">
					<span id="mesa-label" class="property-label"><g:message code="conta.mesa.label" default="Mesa" /></span>
					
						<span class="property-value" aria-labelledby="mesa-label"><g:link controller="mesa" action="show" id="${contaInstance?.mesa?.id}">${contaInstance?.mesa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaInstance?.pedidos}">
				<li class="fieldcontain">
					<span id="pedidos-label" class="property-label"><g:message code="conta.pedidos.label" default="Pedidos" /></span>
					
						<g:each in="${contaInstance.pedidos}" var="p">
						<span class="property-value" aria-labelledby="pedidos-label"><g:link controller="pedido" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${contaInstance?.id}" />
					<g:link class="edit" action="edit" id="${contaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
