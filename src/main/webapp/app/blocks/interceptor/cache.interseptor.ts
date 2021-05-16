import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class CachingInterceptor implements HttpInterceptor {
  private cache: Map<string, [Date, HttpResponse<any>]>;

  constructor() {
    this.cache = new Map<string, [Date, HttpResponse<any>]>();
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // continue if not cacheable.
    if (req.method !== 'GET') {
      return next.handle(req);
    }

    const cachedResponse = this.get(req.urlWithParams);
    if (cachedResponse !== null && cachedResponse !== undefined) return of(cachedResponse.clone());

    return this.sendRequest(req, next);
  }

  sendRequest(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // No headers allowed in npm search request

    return next.handle(req).pipe(
      tap(event => {
        // There may be other events besides the response.
        if (event instanceof HttpResponse) {
          this.set(req.urlWithParams, event); // Update the cache.
        }
      })
    );
  }

  get(key: string): HttpResponse<any> | null {
    const tuple = this.cache.get(key);
    if (!tuple) return null;

    const expires = tuple[0];
    const httpResponse = tuple[1];

    //  Don't observe expired keys
    const now = new Date();
    if (expires && expires.getTime() < now.getTime()) {
      this.cache.delete(key);
      return null;
    }
    return httpResponse;
  }

  set(key: string, value: HttpResponse<any>, ttl = 1000): void {
    if (ttl) {
      const expires = new Date();
      expires.setSeconds(expires.getSeconds() + ttl);
      this.cache.set(key, [expires, value]);
    }
  }
}
