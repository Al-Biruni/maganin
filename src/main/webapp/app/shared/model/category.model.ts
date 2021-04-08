export interface ICategory {
  id?: number;
  categoryTypeId?: string;
  name?: string;
  parentId?: number;
  description?: string;
  imageURL?: string;
  hideSearch?: boolean;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public categoryTypeId?: string,
    public name?: string,
    public parentId?: number,
    public description?: string,
    public imageURL?: string,
    public hideSearch?: boolean
  ) {
    this.hideSearch = this.hideSearch || false;
  }
}
