var remocoes = document.getElementsByClassName('delete') 
for(remocao of remocoes) {
    remocao.addEventListener('click', verificar)
    function verificar(){                  
        confirm('Deseja mesmo fazer a deletação?')
    }
} 