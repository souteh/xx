import { ITypeDeContenuEdoc } from 'app/shared/model/type-de-contenu-edoc.model';

export interface IFondDocumentEdoc {
  id?: number;
  denominationAr?: string;
  denominationFr?: string;
  formatPj?: string;
  reference?: string;
  typesContenus?: ITypeDeContenuEdoc[];
}

export class FondDocumentEdoc implements IFondDocumentEdoc {
  constructor(
    public id?: number,
    public denominationAr?: string,
    public denominationFr?: string,
    public formatPj?: string,
    public reference?: string,
    public typesContenus?: ITypeDeContenuEdoc[]
  ) {}
}
