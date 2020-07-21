import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFondDocumentEdoc, FondDocumentEdoc } from 'app/shared/model/fond-document-edoc.model';
import { FondDocumentEdocService } from './fond-document-edoc.service';

@Component({
  selector: 'jhi-fond-document-edoc-update',
  templateUrl: './fond-document-edoc-update.component.html',
})
export class FondDocumentEdocUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    denominationAr: [],
    denominationFr: [null, [Validators.pattern('[A-Z]+')]],
    formatPj: [],
    reference: [],
  });

  constructor(protected fondDocumentService: FondDocumentEdocService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fondDocument }) => {
      this.updateForm(fondDocument);
    });
  }

  updateForm(fondDocument: IFondDocumentEdoc): void {
    this.editForm.patchValue({
      id: fondDocument.id,
      denominationAr: fondDocument.denominationAr,
      denominationFr: fondDocument.denominationFr,
      formatPj: fondDocument.formatPj,
      reference: fondDocument.reference,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fondDocument = this.createFromForm();
    if (fondDocument.id !== undefined) {
      this.subscribeToSaveResponse(this.fondDocumentService.update(fondDocument));
    } else {
      this.subscribeToSaveResponse(this.fondDocumentService.create(fondDocument));
    }
  }

  private createFromForm(): IFondDocumentEdoc {
    return {
      ...new FondDocumentEdoc(),
      id: this.editForm.get(['id'])!.value,
      denominationAr: this.editForm.get(['denominationAr'])!.value,
      denominationFr: this.editForm.get(['denominationFr'])!.value,
      formatPj: this.editForm.get(['formatPj'])!.value,
      reference: this.editForm.get(['reference'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFondDocumentEdoc>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
