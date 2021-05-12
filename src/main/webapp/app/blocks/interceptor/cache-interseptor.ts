import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

/**
 * Get server response observable by sending request to `next()`.
 * Will add the response to the cache on the way out.
 */
function sendRequest(req: HttpRequest<any>, next: HttpHandler, cache: RequestCacheService): Observable<HttpEvent<any>> {
  // No headers allowed in npm search request
  const noHeaderReq = req.clone({ headers: new HttpHeaders() });

  return next.handle(noHeaderReq).pipe(
    tap(event => {
      // There may be other events besides the response.
      if (event instanceof HttpResponse) {
        cache.set(req.urlWithParams, event); // Update the cache.
      }
    })
  );
}
function isCacheable(req: HttpRequest<any>): boolean {
  if (req.method === 'Get') return false;

  return false;
}
@Injectable()
export class CachingInterceptor implements HttpInterceptor {
  constructor(private cache: RequestCacheService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // continue if not cacheable.
    if (!isCacheable(req)) {
      return next.handle(req);
    }

    const cachedResponse = this.cache.get(req.urlWithParams);
    if (cachedResponse) return cachedResponse[1];
    return sendRequest(req, next, this.cache);
  }
}

@Injectable()
export class RequestCacheService {
  private cache = new Map<string, [Date, HttpResponse<any>]>();

  get(key: string): HttpResponse<any> | null {
    const tuple = this.cache.get(key);
    if (!tuple) return null;

    const expires = tuple[0];
    const httpResponse = tuple[1];

    // Don't observe expired keys
    const now = new Date();
    if (expires && expires.getTime() < now.getTime()) {
      this.cache.delete(key);
      return null;
    }

    return httpResponse;
  }

  set(key: string, value: HttpResponse<any>, ttl = 20000): void {
    if (ttl) {
      const expires = new Date();
      expires.setSeconds(expires.getSeconds() + ttl);
      this.cache.set(key, [expires, value]);
    }
  }
}
