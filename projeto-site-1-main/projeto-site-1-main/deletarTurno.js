var tests = document.getElementsByClassName('delete') 
for(test of tests) {
    test.addEventListener('click', verificar)
    function verificar(){                  
        confirm('Deseja mesmo fazer a deletação?')
    }
} 