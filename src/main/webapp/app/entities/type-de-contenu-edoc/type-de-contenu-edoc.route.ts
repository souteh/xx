import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeDeContenuEdoc, TypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';
import { TypeDeContenuEdocService } from './type-de-contenu-edoc.service';
import { TypeDeContenuEdocComponent } from './type-de-contenu-edoc.component';
import { TypeDeContenuEdocDetailComponent } from './type-de-contenu-edoc-detail.component';
import { TypeDeContenuEdocUpdateComponent } from './type-de-contenu-edoc-update.component';

@Injectable({ providedIn: 'root' })
export class TypeDeContenuEdocResolve implements Resolve<ITypeDeContenuEdoc> {
  constructor(private service: TypeDeContenuEdocService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeDeContenuEdoc> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeDeContenu: HttpResponse<TypeDeContenuEdoc>) => {
          if (typeDeContenu.body) {
            return of(typeDeContenu.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeDeContenuEdoc());
  }
}

export const typeDeContenuRoute: Routes = [
  {
    path: '',
    component: TypeDeContenuEdocComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'xxApp.typeDeContenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeDeContenuEdocDetailComponent,
    resolve: {
      typeDeContenu: TypeDeContenuEdocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'xxApp.typeDeContenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeDeContenuEdocUpdateComponent,
    resolve: {
      typeDeContenu: TypeDeContenuEdocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'xxApp.typeDeContenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeDeContenuEdocUpdateComponent,
    resolve: {
      typeDeContenu: TypeDeContenuEdocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'xxApp.typeDeContenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
