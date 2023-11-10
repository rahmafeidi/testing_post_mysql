import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

 
  constructor(private http:HttpClient) { }
  
   getallproduct(){
    return this.http.get(`${environment.baseurl}/products/all`)
 }

  deleteproduct(id:any){
    return this.http.delete(`${environment.baseurl}/products/${id}`)
  }


  ajotproduct(product:any){
    return this.http.post(`${environment.baseurl}/products/save`,product)
  }
}
