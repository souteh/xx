import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';
import { TypeDeContenuEdocService } from './type-de-contenu-edoc.service';

@Component({
  templateUrl: './type-de-contenu-edoc-delete-dialog.component.html',
})
export class TypeDeContenuEdocDeleteDialogComponent {
  typeDeContenu?: ITypeDeContenuEdoc;

  constructor(
    protected typeDeContenuService: TypeDeContenuEdocService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeDeContenuService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeDeContenuListModification');
      this.activeModal.close();
    });
  }
}
