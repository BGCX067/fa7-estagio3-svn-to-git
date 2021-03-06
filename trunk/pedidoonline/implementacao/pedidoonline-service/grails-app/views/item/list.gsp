
<%@ page import="pedidoonline.service.Item" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'item.label', default: 'Item')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-item" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-item" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'item.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="descricao" title="${message(code: 'item.descricao.label', default: 'Descricao')}" />
					
						<g:sortableColumn property="imagem" title="${message(code: 'item.imagem.label', default: 'Imagem')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'item.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="valor" title="${message(code: 'item.valor.label', default: 'Valor')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemInstanceList}" status="i" var="itemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemInstance.id}">${fieldValue(bean: itemInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: itemInstance, field: "descricao")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "imagem")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "nome")}</td>
					
						<td>${fieldValue(bean: itemInstance, field: "valor")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
