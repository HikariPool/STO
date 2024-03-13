let buyGearsButton = document.getElementById('buyGears')
let serviceButton = document.getElementById('service')
let inventoryButton = document.getElementById('inventory')

if(buyGearsButton != null){
    buyGearsButton.addEventListener('click', () => {
        window.parent.postMessage('/frame/buy_gears_frame.html' )
    });
}

if(serviceButton != null){
    serviceButton.addEventListener('click', () => {
    $.ajax({
         url: '/chat/all',
         type: 'GET',
         success: data => {
             window.parent.postMessage('/frame/car_service_frame.html' + '?chat_id=' + data[0].id)
         }
     });
    });
}

if(inventoryButton != null){
    inventoryButton.addEventListener('click', () => {
         window.parent.postMessage('/frame/inventory_frame.html' )
    });
}