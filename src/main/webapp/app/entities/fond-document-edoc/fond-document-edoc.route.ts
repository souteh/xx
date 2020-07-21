import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFondDocumentEdoc, FondDocumentEdoc } from 'app/shared/model/fond-document-edoc.model';
import { FondDocumentEdocService } from './fond-document-edoc.service';
import { FondDocumentEdocComponent } from './fond-document-edoc.component';
import { FondDocumentEdocDetailComponent } from './fond-document-edoc-detail.component';
import { FondDocumentEdocUpdateComponent } from './fond-document-edoc-update.component';

@Injectable({ providedIn: 'root' })
export class FondDocumentEdocResolve implements Resolve<IFondDocumentEdoc> {
  constructor(private service: FondDocumentEdocService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFondDocumentEdoc> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fondDocument: HttpResponse<FondDocumentEdoc>) => {
          if (fondDocument.body) {
            return of(fondDocument.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FondDocumentEdoc());
  }
}

export const fondDocumentRoute: Routes = [
  {
    path: '',
    component: FondDocumentEdocComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'xxApp.fondDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FondDocumentEdocDetailComponent,
    resolve: {
      fondDocument: FondDocumentEdocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'xxApp.fondDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FondDocumentEdocUpdateComponent,
    resolve: {
      fondDocument: FondDocumentEdocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'xxApp.fondDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FondDocumentEdocUpdateComponent,
    resolve: {
      fondDocument: FondDocumentEdocResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'xxApp.fondDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
