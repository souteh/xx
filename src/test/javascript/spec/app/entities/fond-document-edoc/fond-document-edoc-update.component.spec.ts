import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { XxTestModule } from '../../../test.module';
import { FondDocumentEdocUpdateComponent } from 'app/entities/fond-document-edoc/fond-document-edoc-update.component';
import { FondDocumentEdocService } from 'app/entities/fond-document-edoc/fond-document-edoc.service';
import { FondDocumentEdoc } from 'app/shared/model/fond-document-edoc.model';

describe('Component Tests', () => {
  describe('FondDocumentEdoc Management Update Component', () => {
    let comp: FondDocumentEdocUpdateComponent;
    let fixture: ComponentFixture<FondDocumentEdocUpdateComponent>;
    let service: FondDocumentEdocService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [XxTestModule],
        declarations: [FondDocumentEdocUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FondDocumentEdocUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FondDocumentEdocUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FondDocumentEdocService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FondDocumentEdoc(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new FondDocumentEdoc();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
