const iconCart = document.querySelector('.iconCart');
   const cart = document.querySelector('.cart');
   const productContainer = document.querySelector('.details-container');
   const close = document.querySelector('.close');
   const listCartHTML = document.querySelector('.listCart');

   iconCart.addEventListener('click', () => {
     if (cart.style.right == '-100%') {
       cart.style.right = '0';
       productContainer.style.transform = 'translateX(-400px)';
     } else {
       cart.style.right = '-100%';
       productContainer.style.transform = 'translateX(0)';
     }
   })
   close.addEventListener('click', () => {
     cart.style.right = '-100%';
     productContainer.style.transform = 'translateX(0)';
   })

   document.addEventListener("DOMContentLoaded", async function () {
     const urlParams = new URLSearchParams(window.location.search);
     const productId = urlParams.get('id');

     if (!productId) {
       handleProductNotFound();
       return;
     }

     try {
       const product = await fetchProduct(productId);
       if (!product) {
         handleProductNotFound();
         return;
       }

       displayProductDetails(product);
       setupAddToCartButton(product);

     } catch (error) {
       console.error("Error fetching product data:", error);
     }
   });

   async function fetchProduct(productId) {
     const response = await fetch("product.json");
     const products = await response.json();
     return products.find(p => p.id == productId);
   }

   function displayProductDetails(product) {
     document.getElementById("productName").innerText = product.name;
     document.getElementById("productPrice").innerText = "₹" + product.price;
     document.getElementById("productDescription").innerText = product.description;
     document.getElementById("productImage").src = product.image;
   }

   function setupAddToCartButton(product) {
     document.getElementById("addToCart").addEventListener("click", function () {
       addToCart(product.id);
       alert(`${product.name} added to cart!`);
     });
   }

   function handleProductNotFound() {
     alert("Product not found!");
     window.location.href = "Product.html";
   }

   async function addToCart(productId) {
     let listCart = JSON.parse(localStorage.getItem("listCart")) || {};
     let product = await fetchProduct(productId); // Fetch the product details

     const sizeSelect = document.querySelector('.size-select');
     const selectedSize = sizeSelect.options[sizeSelect.selectedIndex].dataset.size;
     const selectedPrice = sizeSelect.options[sizeSelect.selectedIndex].value;
     

     if (listCart[productId]) {
       listCart[productId].quantity++;
     } else {
       if (product) {
         listCart[productId] = {
           ...product,  // Include all product details
           quantity: 1,
           selectedSize: selectedSize, // Add selectedSize property
           price: selectedPrice
         };
       } else {
         console.error("Product not found for adding to cart:", productId);
         return;
       }
     }
     localStorage.setItem("listCart", JSON.stringify(listCart));
     addCartToHTML();
   }
   // Assuming you have a function to update the cart display
   function updateCartDisplay() {
     // ... your logic to update the cart display ...
   }

   addCartToHTML();

   function updatePrice(selectElement) {
     const selectedOption = selectElement.options[selectElement.selectedIndex];
     const newPrice = selectedOption.value;
     document.getElementById("productPrice").innerText = "₹" + newPrice;
   }


   function changeQuantity(productId, type) {
     let listCart = JSON.parse(localStorage.getItem("listCart")) || {};
     productId = parseInt(productId);

     if (listCart[productId]) {
       if (type === '+') {
         listCart[productId].quantity++;
       } else if (type === '-') {
         listCart[productId].quantity--;
         if (listCart[productId].quantity <= 0) {
           delete listCart[productId];
         }
       }
       localStorage.setItem("listCart", JSON.stringify(listCart));
       addCartToHTML();
     }
   }

   // Event delegation for quantity buttons
   listCartHTML.addEventListener('click', (event) => {
     if (event.target.classList.contains('quantity-btn')) {
       const button = event.target;
       const productId  = button.dataset.productId;
       const type = button.dataset.type;
       changeQuantity(productId , type);
     }
   });

   // Function to update the cart display
   function addCartToHTML() {
     listCartHTML.innerHTML = '';

     let totalHTML = document.querySelector('.totalQuantity');
     let totalQuantity = 0;

     let listCart = JSON.parse(localStorage.getItem("listCart")) || {};
     if (listCart) {
       for (let productId  in listCart) {
         let product = listCart[productId];
         if (product) {
           let newCart = document.createElement('div');
           newCart.classList.add('item');
           newCart.innerHTML =
             `<img src="${product.image}">
             <div class="content">
               <div class="name">${product.name}</div>
               <div class="price">&#8377;${product.price} product</div>
               <div class="size">Size: ${product.selectedSize}</div>
             </div>
             <div class="quantity">
               <button class="quantity-btn" data-product-id="${productId}" data-type="-">-</button>
               <span class="value">${listCart[productId ].quantity}</span>
               <button class="quantity-btn" data-product-id="${productId}" data-type="+">+</button>
             </div>`;
           listCartHTML.appendChild(newCart);
           totalQuantity = totalQuantity + listCart[productId ].quantity;
         }
       }
     }
     totalHTML.innerHTML = totalQuantity;
   }

   // Event listener for 'storage' event to synchronize cart display
   window.addEventListener('storage', (event) => {
     if (event.key === 'listCart') {
       addCartToHTML();
     }
   });
