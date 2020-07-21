import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { XxTestModule } from '../../../test.module';
import { TypeDeContenuEdocUpdateComponent } from 'app/entities/type-de-contenu-edoc/type-de-contenu-edoc-update.component';
import { TypeDeContenuEdocService } from 'app/entities/type-de-contenu-edoc/type-de-contenu-edoc.service';
import { TypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';

describe('Component Tests', () => {
  describe('TypeDeContenuEdoc Management Update Component', () => {
    let comp: TypeDeContenuEdocUpdateComponent;
    let fixture: ComponentFixture<TypeDeContenuEdocUpdateComponent>;
    let service: TypeDeContenuEdocService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [XxTestModule],
        declarations: [TypeDeContenuEdocUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeDeContenuEdocUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeDeContenuEdocUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeDeContenuEdocService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeDeContenuEdoc(123);
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
        const entity = new TypeDeContenuEdoc();
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
