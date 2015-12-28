<%@ page import="pedidoonline.service.Categoria"%>

<div
	class="fieldcontain ${hasErrors(bean: categoriaInstance, field: 'nome', 'error')} ">
	<label for="nome"> <g:message code="categoria.nome.label"
			default="Nome" />
			<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" value="${categoriaInstance?.nome}" />
</div>

<g:if test="${categoriaInstance?.id}">
	<div
		class="fieldcontain ${hasErrors(bean: categoriaInstance, field: 'imagem1', 'error')} ">
		<label for="imagem"> <g:message code="categoria.imagem.label"
				default="" /></label>
		<img class="imagem" id="imagem"
			src="${createLink(controller:'categoria',action:'show_imagem',id:categoriaInstance.ident()) }" />
	</div>
</g:if>

<div
	class="fieldcontain ${hasErrors(bean: categoriaInstance, field: 'imagem', 'error')} ">
	<label for="imagem"> <g:message code="categoria.imagem.label"
			default="Imagem" />
	</label> <input type="file" name="imagem" value="${categoriaInstance?.imagem}" />
</div>



