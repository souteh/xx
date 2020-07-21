import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';
import { TypeDeContenuEdocService } from './type-de-contenu-edoc.service';
import { TypeDeContenuEdocDeleteDialogComponent } from './type-de-contenu-edoc-delete-dialog.component';

@Component({
  selector: 'jhi-type-de-contenu-edoc',
  templateUrl: './type-de-contenu-edoc.component.html',
})
export class TypeDeContenuEdocComponent implements OnInit, OnDestroy {
  typeDeContenus?: ITypeDeContenuEdoc[];
  eventSubscriber?: Subscription;

  constructor(
    protected typeDeContenuService: TypeDeContenuEdocService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.typeDeContenuService.query().subscribe((res: HttpResponse<ITypeDeContenuEdoc[]>) => (this.typeDeContenus = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTypeDeContenus();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITypeDeContenuEdoc): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTypeDeContenus(): void {
    this.eventSubscriber = this.eventManager.subscribe('typeDeContenuListModification', () => this.loadAll());
  }

  delete(typeDeContenu: ITypeDeContenuEdoc): void {
    const modalRef = this.modalService.open(TypeDeContenuEdocDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.typeDeContenu = typeDeContenu;
  }
}
