import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeDeContenuEdoc, TypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';
import { TypeDeContenuEdocService } from './type-de-contenu-edoc.service';
import { IFondDocumentEdoc } from 'app/shared/model/fond-document-edoc.model';
import { FondDocumentEdocService } from 'app/entities/fond-document-edoc/fond-document-edoc.service';

@Component({
  selector: 'jhi-type-de-contenu-edoc-update',
  templateUrl: './type-de-contenu-edoc-update.component.html',
})
export class TypeDeContenuEdocUpdateComponent implements OnInit {
  isSaving = false;
  fonddocuments: IFondDocumentEdoc[] = [];

  editForm = this.fb.group({
    id: [],
    denominationAr: [],
    denominationFr: [],
    reference: [null, [Validators.required, Validators.minLength(5), Validators.maxLength(10)]],
    code: [null, [Validators.required]],
    fondDocumentId: [],
  });

  constructor(
    protected typeDeContenuService: TypeDeContenuEdocService,
    protected fondDocumentService: FondDocumentEdocService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeDeContenu }) => {
      this.updateForm(typeDeContenu);

      this.fondDocumentService.query().subscribe((res: HttpResponse<IFondDocumentEdoc[]>) => (this.fonddocuments = res.body || []));
    });
  }

  updateForm(typeDeContenu: ITypeDeContenuEdoc): void {
    this.editForm.patchValue({
      id: typeDeContenu.id,
      denominationAr: typeDeContenu.denominationAr,
      denominationFr: typeDeContenu.denominationFr,
      reference: typeDeContenu.reference,
      code: typeDeContenu.code,
      fondDocumentId: typeDeContenu.fondDocumentId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeDeContenu = this.createFromForm();
    if (typeDeContenu.id !== undefined) {
      this.subscribeToSaveResponse(this.typeDeContenuService.update(typeDeContenu));
    } else {
      this.subscribeToSaveResponse(this.typeDeContenuService.create(typeDeContenu));
    }
  }

  private createFromForm(): ITypeDeContenuEdoc {
    return {
      ...new TypeDeContenuEdoc(),
      id: this.editForm.get(['id'])!.value,
      denominationAr: this.editForm.get(['denominationAr'])!.value,
      denominationFr: this.editForm.get(['denominationFr'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      code: this.editForm.get(['code'])!.value,
      fondDocumentId: this.editForm.get(['fondDocumentId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeDeContenuEdoc>>): void {
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

  trackById(index: number, item: IFondDocumentEdoc): any {
    return item.id;
  }
}
