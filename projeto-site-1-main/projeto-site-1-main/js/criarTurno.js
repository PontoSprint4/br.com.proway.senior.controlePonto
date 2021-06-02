var criarT = document.getElementById('criarTurno')
criarT.addEventListener('click', verificar) 

function verificar(){ 
    debugger;
    // Desabilita o botão: editarTurno.
    document.getElementById('editarTurno').disabled = true;
 
    // Desabilita o botão: criarTurno.
    document.getElementById('criarTurno').disabled = true;
    
    // Troca o conteúdo (vazia) da <div>: formularioTurno.
    // Por um formulário: registroTurno. 
    var formularioTurno = document.getElementById('formularioTurno') 
    formularioTurno.innerHTML = '<form id="registroTurno"><fieldset id="dadosTurno"><legend>Registro de Turno</legend><p><label for="cNomeT">Nome do Turno: </label><input type="text" name="tNomeT" id="cNomeT" size="20" maxlength="20" placeholder="..."></p><p><label for="cHoraI">Hora de Início: </label><input type="time" name="tHoraI" id="cHoraI"></p><p><label for="cHoraF">Hora do Fim: </label><input type="time" name="tHoraF" id="cHoraF"></p></fieldset></form>'
    
    // Cria um botão.
    var elemento = document.createElement('button');

    // Define um ID para o botão criado (objetivo: possibilidade de referenciar o mesmo futuramente).
    elemento.setAttribute("id", "salvarTurno")

    // Na página principal, adiciona o botão: salvarTurno (obrigatório para haver o registro de um mesmo).
    var button = document.getElementById('buttonTurno')
    button.append(elemento)
    elemento.innerHTML = 'Salvar Turno' 
    
    // Linka o evento 'click' no botão: salvarTurno.
    // "Chama" a função SALVAR.
    elemento.addEventListener('click', salvar)

}     

function salvar(){      
    
    // Remove o formulário: registroTurno.
    var formularioTurno = document.getElementById('formularioTurno') 
    formularioTurno.removeChild(document.getElementById('registroTurno'));

    // Remove o botão: salvarTurno.
    var button = document.getElementById('buttonTurno');
    button.removeChild(document.getElementById('salvarTurno'));

    // Habilita o botão: criarTurno.
    document.getElementById('criarTurno').disabled = false 

    // Habilita o botão: editarTurno.
    document.getElementById('editarTurno').disabled = false 
    
} 