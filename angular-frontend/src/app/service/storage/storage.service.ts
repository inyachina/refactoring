// import { Injectable } from '@angular/core';
// import { Subject } from 'rxjs';
// import { Storage } from './storage.type';
//
// @Injectable({
//   providedIn: 'root',
// })
// export class StorageService {
//   public storageChange$ = new Subject<Storage>();
//
//   public setItem(type: StorageType, value: any): void {
//     localStorage.setItem(type, JSON.stringify(value));
//     this.storageChange$.next({ type, value });
//   }
//
//   public getItem<T>(type: StorageType): T {
//     // tslint:disable-next-line:no-non-null-assertion
//     const item = localStorage.getItem(type)!;
//
//     return JSON.parse(item);
//   }
//
//   public removeItem(type: StorageType): void {
//     localStorage.removeItem(type);
//     this.storageChange$.next({ type });
//   }
//
//   /**
//    * Нельзя использоваться .clear(),
//    * т.к. другие части приложения (/admin/, /contractor/)
//    * могут быть на том же домене => тот же сторедж
//    */
//   public clearStorage(): void {
//     Object.keys(StorageType).forEach((k: string) => {
//       const key = k as keyof typeof StorageType;
//
//       this.removeItem(StorageType[key]);
//     });
//   }
// }
