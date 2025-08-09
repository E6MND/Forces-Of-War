package org.xwalk.core.internal;

import org.chromium.net.NetError;

class ErrorCodeConversionHelper {
    ErrorCodeConversionHelper() {
    }

    static int convertErrorCode(int netError) {
        switch (netError) {
            case NetError.ERR_MISCONFIGURED_AUTH_ENVIRONMENT:
            case NetError.ERR_MISSING_AUTH_CREDENTIALS:
            case NetError.ERR_INVALID_AUTH_CREDENTIALS:
                return -4;
            case NetError.ERR_UNSUPPORTED_AUTH_SCHEME:
                return -3;
            case NetError.ERR_NETWORK_IO_SUSPENDED:
            case -1:
                return -7;
            case NetError.ERR_UNEXPECTED_PROXY_AUTH:
            case NetError.ERR_PROXY_CONNECTION_FAILED:
            case NetError.ERR_PROXY_AUTH_REQUESTED:
            case NetError.ERR_PROXY_AUTH_UNSUPPORTED:
                return -5;
            case NetError.ERR_TOO_MANY_REDIRECTS:
                return -9;
            case NetError.ERR_UNKNOWN_URL_SCHEME:
            case NetError.ERR_DISALLOWED_URL_SCHEME:
                return -10;
            case NetError.ERR_INVALID_URL:
                return -12;
            case NetError.ERR_CERT_NON_UNIQUE_NAME:
            case NetError.ERR_CERT_WEAK_SIGNATURE_ALGORITHM:
            case NetError.ERR_CERT_INVALID:
            case NetError.ERR_CERT_REVOKED:
            case NetError.ERR_CERT_UNABLE_TO_CHECK_REVOCATION:
            case NetError.ERR_CERT_NO_REVOCATION_MECHANISM:
            case NetError.ERR_CERT_CONTAINS_ERRORS:
            case NetError.ERR_CERT_AUTHORITY_INVALID:
            case NetError.ERR_CERT_DATE_INVALID:
            case NetError.ERR_CERT_COMMON_NAME_INVALID:
                return 0;
            case NetError.ERR_NAME_RESOLUTION_FAILED:
            case NetError.ERR_ADDRESS_UNREACHABLE:
            case NetError.ERR_ADDRESS_INVALID:
            case NetError.ERR_INTERNET_DISCONNECTED:
            case NetError.ERR_NAME_NOT_RESOLVED:
                return -2;
            case NetError.ERR_SSL_CLIENT_AUTH_CERT_NO_PRIVATE_KEY:
            case NetError.ERR_SSL_CLIENT_AUTH_PRIVATE_KEY_ACCESS_DENIED:
            case NetError.ERR_SSL_WEAK_SERVER_EPHEMERAL_DH_KEY:
            case NetError.ERR_SSL_UNSAFE_NEGOTIATION:
            case NetError.ERR_SSL_BAD_RECORD_MAC_ALERT:
            case NetError.ERR_SSL_DECOMPRESSION_FAILURE_ALERT:
            case NetError.ERR_SSL_NO_RENEGOTIATION:
            case NetError.ERR_BAD_SSL_CLIENT_AUTH_CERT:
            case NetError.ERR_CERT_ERROR_IN_SSL_RENEGOTIATION:
            case NetError.ERR_SSL_RENEGOTIATION_REQUESTED:
            case NetError.ERR_SSL_VERSION_OR_CIPHER_MISMATCH:
            case NetError.ERR_NO_SSL_VERSIONS_ENABLED:
            case NetError.ERR_TUNNEL_CONNECTION_FAILED:
            case -110:
            case NetError.ERR_SSL_PROTOCOL_ERROR:
                return -11;
            case NetError.ERR_HOST_RESOLVER_QUEUE_TOO_LARGE:
            case -13:
            case -12:
                return -15;
            case NetError.ERR_CONNECTION_TIMED_OUT:
            case -7:
                return -8;
            case NetError.ERR_CONNECTION_FAILED:
            case NetError.ERR_CONNECTION_ABORTED:
            case NetError.ERR_CONNECTION_REFUSED:
            case NetError.ERR_CONNECTION_RESET:
            case NetError.ERR_CONNECTION_CLOSED:
            case -15:
                return -6;
            case -14:
                return -14;
            case -8:
                return -13;
            default:
                return -1;
        }
    }
}
