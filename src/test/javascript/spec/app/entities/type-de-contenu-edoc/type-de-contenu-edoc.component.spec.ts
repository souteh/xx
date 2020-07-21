import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XxTestModule } from '../../../test.module';
import { TypeDeContenuEdocComponent } from 'app/entities/type-de-contenu-edoc/type-de-contenu-edoc.component';
import { TypeDeContenuEdocService } from 'app/entities/type-de-contenu-edoc/type-de-contenu-edoc.service';
import { TypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';

describe('Component Tests', () => {
  describe('TypeDeContenuEdoc Management Component', () => {
    let comp: TypeDeContenuEdocComponent;
    let fixture: ComponentFixture<TypeDeContenuEdocComponent>;
    let service: TypeDeContenuEdocService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [XxTestModule],
        declarations: [TypeDeContenuEdocComponent],
      })
        .overrideTemplate(TypeDeContenuEdocComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeDeContenuEdocComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeDeContenuEdocService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeDeContenuEdoc(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeDeContenus && comp.typeDeContenus[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
