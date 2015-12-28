
<%@ page import="pedidoonline.service.Conta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'conta.label', default: 'Conta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-conta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-conta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'conta.codigo.label', default: 'Codigo')}" />
					
						<th><g:message code="conta.fatura.label" default="Fatura" /></th>
					
						<th><g:message code="conta.mesa.label" default="Mesa" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contaInstanceList}" status="i" var="contaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${contaInstance.id}">${fieldValue(bean: contaInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: contaInstance, field: "fatura")}</td>
					
						<td>${fieldValue(bean: contaInstance, field: "mesa")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
