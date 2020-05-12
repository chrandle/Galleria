import { Component, Input } from '@angular/core';
import { trigger, transition, style, animate } from '@angular/animations';

@Component({
  selector: 'carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css'],
  animations: [
    trigger('carouselAnimation',[
      transition('void=>*',[
        style({opacity:0}),
        animate('600ms',style({opacity:1}))
      ]),
      transition('*=>void',[
        animate('600ms',style({opacity:0}))
      ])
    ])
  ]
})
export class CarouselComponent{
  public slides = [
    {src: "../assets/test_images/test_(1).jpg"},
    {src: "../assets/test_images/test_(2).jpg"},
    {src: "../assets/test_images/test_(3).jpg"},
    {src: "../assets/test_images/test_(4).jpg"},
    {src: "../assets/test_images/test_(5).jpg"},
    {src: "../assets/test_images/test_(1).jpeg"},
    {src: "../assets/test_images/test_(1).png"}
  ];
  
  currentSlide = 0;


  constructor() { }

     onPrevClick() {
       const previous = this.currentSlide-1;
       this.currentSlide = previous < 0 ? this.slides.length-1:previous;
      console.log("Previous clicked current slide is ",this.currentSlide);
    }

    
    onNextClick() {
      const next = this.currentSlide+1;
      this.currentSlide = next === this.slides.length ? 0:next;
     console.log("Next clicked current slide is ",this.currentSlide);
   }

}
