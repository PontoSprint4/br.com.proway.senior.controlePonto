var criarT = document.getElementById('criarTurno')
criarT.addEventListener('click', verificar)    
function verificar(){   
    var formularioTurno = document.getElementById('formularioTurno') 
    formularioTurno.innerHTML = '<form><fieldset id="usuario"><legend>Identificação do Usuário</legend><p><label for="cNom">Nome: </label><input type="text" name="tNom" id="cNom" size="50" maxlength="50" placeholder="Nome Completo"></p><p><label for="cSenha">Senha: </label> <input type="password" name="tSenha" id="cSenha" size="8" maxlength="8" placeholder="8 Dígitos"></p>  <p><label for="cEm">E-mail:</label> <input type="email" name="tEm" id="cEm" size="50" maxlength="50"></p><fieldset id="sexo"><input type="radio" name="tSexo" id="cMasc" checked> <label for="cMasc">Masculino</label><br><input type="radio" name="tSexo" id="cFem"> <label for="cFem">Feminino</label></fieldset><p><label for="cNasc">Nascimento: </label><input type="date" name="tNasc" id="cNasc"></p></fieldset></form>'
}     