let listItemContainer = document.getElementById('listItemContainer')

let inventoryItemTemplate
let paramItem
let buyTemplate

loadTemplates()

reloadList()

function showItems(items){
     for(var i = 0; i < items.length; i++){
         inventoryItem = createElementFromTemplate(inventoryItemTemplate)
         tbody = inventoryItem.getElementsByTagName('tbody')[0]

         item = items[i]
         if(item.imagePath != '/upload/' && item.imagePath != null && item.imagePath != undefined){
            inventoryItem.getElementsByTagName('img')[0].src = item.imagePath
         }

         for(key of Object.keys(item.params)){
             paramTemplate = createElementFromTemplate(paramItem)
             paramTemplate.querySelectorAll('[name="name"]')[0].value = key
             paramTemplate.querySelectorAll('[name="value"]')[0].value = item[key]

             tbody.append(paramTemplate)
         }
         inventoryItem.querySelectorAll('[name="buy"]')[0].addEventListener('click', () => {
             openBuyForm(item)
         })

         enable(inventoryItem, false)

         listItemContainer.append(inventoryItem)
     }
}

function openBuyForm(item){
    listItemContainer.innerHTML = ''
    buyPopup = createElementFromTemplate(buyTemplate)
    buyPopup.querySelectorAll('[name="sendOrder"]')[0].addEventListener('click', () => {
          address = buyPopup.querySelectorAll('[name="value"]')[0].value
          order = {
            "productId" : item.id,
            "address": address
          }

          $.ajax({
                  url: '/order/create',
                  type: 'POST',
                  data: JSON.stringify(order),
                  success: (data) => {
                      window.location.href = '/frame/success.html';
                  }
          });
    })

    listItemContainer.append(buyPopup)
}

function enable(inventoryItem, doEnable){
    if(doEnable){
        for(input of inventoryItem.getElementsByTagName('input'))
            input.removeAttribute('disabled')
    }else{
        for(input of inventoryItem.getElementsByTagName('input'))
            input.setAttribute('disabled','')
    }
}

function reloadList(){
        listItemContainer.innerHTML = ''

        $.ajax({
                url: '/inventory/all',
                type: 'GET',
                success: (data) => showItems(data)
            });
}

function createElementFromTemplate(html){
      const template = document.createElement('template');
      template.innerHTML = html;
      return template.content.children[0];
}

function loadTemplates(){
    $.ajax({
        url: '/frame/productItem.html',
        type: 'GET',
        success: (data) => {
            inventoryItemTemplate = data
        }
    });

    $.ajax({
        url: '/frame/paramItem.html',
        type: 'GET',
        success: (data) => {
            paramItem = data
        }
    });

    $.ajax({
            url: '/frame/buy.html',
            type: 'GET',
            success: (data) => {
                buyTemplate = data
            }
        });
}