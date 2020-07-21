import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFondDocumentEdoc } from 'app/shared/model/fond-document-edoc.model';
import { FondDocumentEdocService } from './fond-document-edoc.service';

@Component({
  templateUrl: './fond-document-edoc-delete-dialog.component.html',
})
export class FondDocumentEdocDeleteDialogComponent {
  fondDocument?: IFondDocumentEdoc;

  constructor(
    protected fondDocumentService: FondDocumentEdocService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fondDocumentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fondDocumentListModification');
      this.activeModal.close();
    });
  }
}
