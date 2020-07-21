import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';

@Component({
  selector: 'jhi-type-de-contenu-edoc-detail',
  templateUrl: './type-de-contenu-edoc-detail.component.html',
})
export class TypeDeContenuEdocDetailComponent implements OnInit {
  typeDeContenu: ITypeDeContenuEdoc | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeDeContenu }) => (this.typeDeContenu = typeDeContenu));
  }

  previousState(): void {
    window.history.back();
  }
}
