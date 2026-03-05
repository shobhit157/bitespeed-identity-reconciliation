## Bitespeed Identify App API

### Base URL


---

### Endpoint: Identify Contact

- **URL:** `/contacts/identify`  
- **Method:** `GET`  
- **Full URL:** `https://bitespeed-identify-app-p0gd.onrender.com/contacts/identify`  

---

### How to Test in Postman

1. Open **Postman**  
2. Select **GET** request  
3. Paste the URL:
4. Pls specify "Email" , "PhoneNumber" in request body -> raw b/w {...}
5. example:
   { "Email" :"", "PhoneNumber":""}
6. Hit **Send**  
7. You should receive a **JSON response** from the PostgreSQL database containing contact identification results.
