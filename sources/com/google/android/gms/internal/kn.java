package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

public final class kn implements People {

    private static abstract class a extends Plus.a<People.LoadPeopleResult> {
        private a() {
        }

        /* renamed from: ao */
        public People.LoadPeopleResult c(final Status status) {
            return new People.LoadPeopleResult() {
                public String getNextPageToken() {
                    return null;
                }

                public PersonBuffer getPersonBuffer() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    public Person getCurrentPerson(GoogleApiClient googleApiClient) {
        return Plus.a(googleApiClient, Plus.yE).getCurrentPerson();
    }

    public PendingResult<People.LoadPeopleResult> load(GoogleApiClient googleApiClient, final Collection<String> personIds) {
        return googleApiClient.a(new a() {
            /* access modifiers changed from: protected */
            public void a(e eVar) {
                eVar.a((a.d<People.LoadPeopleResult>) this, (Collection<String>) personIds);
            }
        });
    }

    public PendingResult<People.LoadPeopleResult> load(GoogleApiClient googleApiClient, final String... personIds) {
        return googleApiClient.a(new a() {
            /* access modifiers changed from: protected */
            public void a(e eVar) {
                eVar.d(this, personIds);
            }
        });
    }

    public PendingResult<People.LoadPeopleResult> loadConnected(GoogleApiClient googleApiClient) {
        return googleApiClient.a(new a() {
            /* access modifiers changed from: protected */
            public void a(e eVar) {
                eVar.l(this);
            }
        });
    }

    public PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, final int orderBy, final String pageToken) {
        return googleApiClient.a(new a() {
            /* access modifiers changed from: protected */
            public void a(e eVar) {
                a(eVar.a((a.d<People.LoadPeopleResult>) this, orderBy, pageToken));
            }
        });
    }

    public PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, final String pageToken) {
        return googleApiClient.a(new a() {
            /* access modifiers changed from: protected */
            public void a(e eVar) {
                a(eVar.r(this, pageToken));
            }
        });
    }
}
