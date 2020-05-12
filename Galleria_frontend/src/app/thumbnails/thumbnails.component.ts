import { Component, OnInit } from '@angular/core';

export interface Tile {
  // cols: number;
  // rows: number;
  text: string;
  src: string;
}

@Component({
  selector: 'thumbnails',
  templateUrl: './thumbnails.component.html',
  styleUrls: ['./thumbnails.component.css']
})
export class ThumbnailsComponent  {


    tiles: Tile[] = [
    {text: 'One', src: "../assets/test_images/test_(1).jpg"},
    {text: 'One', src: "../assets/test_images/test_(2).jpg"},
    {text: 'One', src: "../assets/test_images/test_(3).jpg"},
    {text: 'One', src: "../assets/test_images/test_(4).jpg"},
    {text: 'One', src: "../assets/test_images/test_(5).jpg"},
    {text: 'One', src: "../assets/test_images/test_(1).jpeg"},
    {text: 'One', src: "../assets/test_images/test_(1).png"}
  ];


}
