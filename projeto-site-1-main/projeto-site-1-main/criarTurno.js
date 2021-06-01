var criarT = document.getElementById('criarTurno')
criarT.addEventListener('click', verificar)    
function verificar(){   
    var formularioTurno = document.getElementById('formularioTurno') 
    formularioTurno.innerHTML = '<form><fieldset id="turno"><legend>Registro de Turno</legend>    <p><label for="cNomeT">Nome do Turno: </label><input type="text" name="tNomeT" id="cNomeT" size="20" maxlength="20" placeholder="..."></p><p><label for="cHoraI">Hora de Início: </label><input type="time" name="tHoraI" id="cHoraI"></p><p><label for="cHoraF">Hora do Fim: </label><input type="time" name="tHoraF" id="cHoraF"></p></fieldset></form>'
}     