<div id="menu">
    <nobr>
        <g:if test="${session.usuario}">
            <b>${session.usuario?.nombre} ${session.usuario?.apellidos}</b> |
            <g:link controller="usuario" action="logout">Log-out</g:link>
        </g:if>
        <g:else>
            <g:link controller="usuario" action="login">Log-in</g:link>
        </g:else>
    </nobr>
</div>