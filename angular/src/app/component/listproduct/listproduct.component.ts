import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from 'src/app/service/product.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listproduct',
  templateUrl: './listproduct.component.html',
  styleUrls: ['./listproduct.component.css']
})
export class ListproductComponent implements OnInit {
  listproduct:any
  form :FormGroup

  closeResult = '';
  p:number=1 //3 eme etape pagination
    constructor(private formBuilder: FormBuilder,private product :ProductService ,private modalService: NgbModal) { }
  
    ngOnInit(): void {

      this.form = this.formBuilder.group({
        ref: ['', Validators.required],
        name: ['', Validators.required],
        price: ['', Validators.required],
        qte: ['', Validators.required],
        description: ['', Validators.required],
      });
      this.allproduct()
    }
    allproduct(){
  this.product.getallproduct().subscribe((res:any)=>{
  this.listproduct=res
  console.log("list product est :",this.listproduct);
  
  })
    }
  
  
    delete(id:any){
      Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.product.deleteproduct(id).subscribe((res:any)=>{
            console.log("product deleted",res);
            this.allproduct()
          })
          Swal.fire(
            'Deleted!',
            'Your file has been deleted.',
            'success'
          )
  
        }
      })
    }
    ajout() {
      this.product.ajotproduct(this.form.value)
      .subscribe((res: any) => {
          Swal.fire('product added');
          console.log(res);
          this.allproduct()
        });
    }
    open( product: any) {
   
      this.modalService
        .open(product,{ ariaLabelledBy: 'modal-basic-title' })
        .result.then(
          (result) => {
            this.closeResult = `Closed with: ${result}`;
          },
          (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
          }
        );
    }
  
    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return `with: ${reason}`;
      }
    }
  }
