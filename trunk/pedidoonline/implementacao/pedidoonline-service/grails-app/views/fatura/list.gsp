
<%@ page import="pedidoonline.service.Fatura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fatura.label', default: 'Fatura')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fatura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fatura" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="fatura.conta.label" default="Conta" /></th>
					
						<g:sortableColumn property="valorParcial" title="${message(code: 'fatura.valorParcial.label', default: 'Valor Parcial')}" />
					
						<g:sortableColumn property="valorTotal" title="${message(code: 'fatura.valorTotal.label', default: 'Valor Total')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${faturaInstanceList}" status="i" var="faturaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${faturaInstance.id}">${fieldValue(bean: faturaInstance, field: "conta")}</g:link></td>
					
						<td>${fieldValue(bean: faturaInstance, field: "valorParcial")}</td>
					
						<td>${fieldValue(bean: faturaInstance, field: "valorTotal")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${faturaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
