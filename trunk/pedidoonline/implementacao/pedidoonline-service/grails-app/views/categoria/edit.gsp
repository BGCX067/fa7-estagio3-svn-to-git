<%@ page import="pedidoonline.service.Categoria"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'categoria.label', default: 'Categoria')}" />
<title><g:message code="default.edit.label" args="[entityName]" /></title>
	<g:javascript library="jquery" plugin="jquery"/>
</head>
<body>
	<a href="#edit-categoria" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="edit-categoria" class="content scaffold-edit" role="main">
		<h1>
			<g:message code="default.edit.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${categoriaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${categoriaInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form method="post" enctype="multipart/form-data">
			<g:hiddenField name="id" value="${categoriaInstance?.id}" />
			<g:hiddenField name="version" value="${categoriaInstance?.version}" />
			<fieldset class="form">
				<g:render template="form" />
			</fieldset>
			<fieldset class="buttons">
				<g:actionSubmit class="save" action="update"
					value="${message(code: 'default.button.update.label', default: 'Update')}" />
				<g:actionSubmit class="delete" action="delete"
					value="${message(code: 'default.button.delete.label', default: 'Delete')}"
					formnovalidate=""
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</fieldset>
		</g:form>
	</div>
	<script type="text/javascript">
		var readURL = function (input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		            $('#imagem').attr('src', e.target.result);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		};

		$("input[type='file']").change(function(){
		    readURL(this);
		});
	</script>
</body>
</html>
