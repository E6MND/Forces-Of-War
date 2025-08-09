package com.facebook.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.facebook.FacebookException;
import com.facebook.android.R;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.ImageResponse;
import com.facebook.model.GraphObject;
import java.net.URI;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

class GraphObjectAdapter<T extends GraphObject> extends BaseAdapter implements SectionIndexer {
    static final /* synthetic */ boolean $assertionsDisabled = (!GraphObjectAdapter.class.desiredAssertionStatus());
    private static final int ACTIVITY_CIRCLE_VIEW_TYPE = 2;
    private static final int DISPLAY_SECTIONS_THRESHOLD = 1;
    private static final int GRAPH_OBJECT_VIEW_TYPE = 1;
    private static final int HEADER_VIEW_TYPE = 0;
    private static final String ID = "id";
    private static final int MAX_PREFETCHED_PICTURES = 20;
    private static final String NAME = "name";
    private static final String PICTURE = "picture";
    private Context context;
    private GraphObjectCursor<T> cursor;
    private DataNeededListener dataNeededListener;
    private boolean displaySections;
    private Filter<T> filter;
    private Map<String, T> graphObjectsById = new HashMap();
    private Map<String, ArrayList<T>> graphObjectsBySection = new HashMap();
    private String groupByField;
    private final LayoutInflater inflater;
    private OnErrorListener onErrorListener;
    private final Map<String, ImageRequest> pendingRequests = new HashMap();
    private Map<String, ImageResponse> prefetchedPictureCache = new HashMap();
    private ArrayList<String> prefetchedProfilePictureIds = new ArrayList<>();
    private List<String> sectionKeys = new ArrayList();
    private boolean showCheckbox;
    private boolean showPicture;
    /* access modifiers changed from: private */
    public List<String> sortFields;

    public interface DataNeededListener {
        void onDataNeeded();
    }

    interface Filter<T> {
        boolean includeItem(T t);
    }

    private interface ItemPicture extends GraphObject {
        ItemPictureData getData();
    }

    private interface ItemPictureData extends GraphObject {
        String getUrl();
    }

    public interface OnErrorListener {
        void onError(GraphObjectAdapter<?> graphObjectAdapter, FacebookException facebookException);
    }

    public static class SectionAndItem<T extends GraphObject> {
        public T graphObject;
        public String sectionKey;

        public enum Type {
            GRAPH_OBJECT,
            SECTION_HEADER,
            ACTIVITY_CIRCLE
        }

        public SectionAndItem(String sectionKey2, T graphObject2) {
            this.sectionKey = sectionKey2;
            this.graphObject = graphObject2;
        }

        public Type getType() {
            if (this.sectionKey == null) {
                return Type.ACTIVITY_CIRCLE;
            }
            if (this.graphObject == null) {
                return Type.SECTION_HEADER;
            }
            return Type.GRAPH_OBJECT;
        }
    }

    public GraphObjectAdapter(Context context2) {
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

    public List<String> getSortFields() {
        return this.sortFields;
    }

    public void setSortFields(List<String> sortFields2) {
        this.sortFields = sortFields2;
    }

    public String getGroupByField() {
        return this.groupByField;
    }

    public void setGroupByField(String groupByField2) {
        this.groupByField = groupByField2;
    }

    public boolean getShowPicture() {
        return this.showPicture;
    }

    public void setShowPicture(boolean showPicture2) {
        this.showPicture = showPicture2;
    }

    public boolean getShowCheckbox() {
        return this.showCheckbox;
    }

    public void setShowCheckbox(boolean showCheckbox2) {
        this.showCheckbox = showCheckbox2;
    }

    public DataNeededListener getDataNeededListener() {
        return this.dataNeededListener;
    }

    public void setDataNeededListener(DataNeededListener dataNeededListener2) {
        this.dataNeededListener = dataNeededListener2;
    }

    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener2) {
        this.onErrorListener = onErrorListener2;
    }

    public GraphObjectCursor<T> getCursor() {
        return this.cursor;
    }

    public boolean changeCursor(GraphObjectCursor<T> cursor2) {
        if (this.cursor == cursor2) {
            return false;
        }
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = cursor2;
        rebuildAndNotify();
        return true;
    }

    public void rebuildAndNotify() {
        rebuildSections();
        notifyDataSetChanged();
    }

    public void prioritizeViewRange(int firstVisibleItem, int lastVisibleItem, int prefetchBuffer) {
        ImageRequest request;
        if (lastVisibleItem >= firstVisibleItem && this.sectionKeys.size() != 0) {
            for (int i = lastVisibleItem; i >= 0; i--) {
                SectionAndItem<T> sectionAndItem = getSectionAndItem(i);
                if (!(sectionAndItem.graphObject == null || (request = this.pendingRequests.get(getIdOfGraphObject(sectionAndItem.graphObject))) == null)) {
                    ImageDownloader.prioritizeRequest(request);
                }
            }
            int start = Math.max(0, firstVisibleItem - prefetchBuffer);
            int end = Math.min(lastVisibleItem + prefetchBuffer, getCount() - 1);
            ArrayList<T> graphObjectsToPrefetchPicturesFor = new ArrayList<>();
            for (int i2 = start; i2 < firstVisibleItem; i2++) {
                SectionAndItem<T> sectionAndItem2 = getSectionAndItem(i2);
                if (sectionAndItem2.graphObject != null) {
                    graphObjectsToPrefetchPicturesFor.add(sectionAndItem2.graphObject);
                }
            }
            for (int i3 = lastVisibleItem + 1; i3 <= end; i3++) {
                SectionAndItem<T> sectionAndItem3 = getSectionAndItem(i3);
                if (sectionAndItem3.graphObject != null) {
                    graphObjectsToPrefetchPicturesFor.add(sectionAndItem3.graphObject);
                }
            }
            Iterator i$ = graphObjectsToPrefetchPicturesFor.iterator();
            while (i$.hasNext()) {
                T graphObject = (GraphObject) i$.next();
                URI uri = getPictureUriOfGraphObject(graphObject);
                String id = getIdOfGraphObject(graphObject);
                boolean alreadyPrefetching = this.prefetchedProfilePictureIds.remove(id);
                this.prefetchedProfilePictureIds.add(id);
                if (!alreadyPrefetching) {
                    downloadProfilePicture(id, uri, (ImageView) null);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getSectionKeyOfGraphObject(T graphObject) {
        String result = null;
        if (!(this.groupByField == null || (result = (String) graphObject.getProperty(this.groupByField)) == null || result.length() <= 0)) {
            result = result.substring(0, 1).toUpperCase();
        }
        return result != null ? result : "";
    }

    /* access modifiers changed from: protected */
    public CharSequence getTitleOfGraphObject(T graphObject) {
        return (String) graphObject.getProperty(NAME);
    }

    /* access modifiers changed from: protected */
    public CharSequence getSubTitleOfGraphObject(T t) {
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.net.URI getPictureUriOfGraphObject(T r7) {
        /*
            r6 = this;
            r3 = 0
            java.lang.String r4 = "picture"
            java.lang.Object r2 = r7.getProperty(r4)
            boolean r4 = r2 instanceof java.lang.String
            if (r4 == 0) goto L_0x0016
            r3 = r2
            java.lang.String r3 = (java.lang.String) r3
        L_0x000e:
            if (r3 == 0) goto L_0x0034
            java.net.URI r4 = new java.net.URI     // Catch:{ URISyntaxException -> 0x0033 }
            r4.<init>(r3)     // Catch:{ URISyntaxException -> 0x0033 }
        L_0x0015:
            return r4
        L_0x0016:
            boolean r4 = r2 instanceof org.json.JSONObject
            if (r4 == 0) goto L_0x000e
            org.json.JSONObject r2 = (org.json.JSONObject) r2
            com.facebook.model.GraphObject r4 = com.facebook.model.GraphObject.Factory.create((org.json.JSONObject) r2)
            java.lang.Class<com.facebook.widget.GraphObjectAdapter$ItemPicture> r5 = com.facebook.widget.GraphObjectAdapter.ItemPicture.class
            com.facebook.model.GraphObject r1 = r4.cast(r5)
            com.facebook.widget.GraphObjectAdapter$ItemPicture r1 = (com.facebook.widget.GraphObjectAdapter.ItemPicture) r1
            com.facebook.widget.GraphObjectAdapter$ItemPictureData r0 = r1.getData()
            if (r0 == 0) goto L_0x000e
            java.lang.String r3 = r0.getUrl()
            goto L_0x000e
        L_0x0033:
            r4 = move-exception
        L_0x0034:
            r4 = 0
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.widget.GraphObjectAdapter.getPictureUriOfGraphObject(com.facebook.model.GraphObject):java.net.URI");
    }

    /* access modifiers changed from: protected */
    public View getSectionHeaderView(String sectionHeader, View convertView, ViewGroup parent) {
        TextView result = (TextView) convertView;
        if (result == null) {
            result = (TextView) this.inflater.inflate(R.layout.com_facebook_picker_list_section_header, (ViewGroup) null);
        }
        result.setText(sectionHeader);
        return result;
    }

    /* access modifiers changed from: protected */
    public View getGraphObjectView(T graphObject, View convertView, ViewGroup parent) {
        View result = convertView;
        if (result == null) {
            result = createGraphObjectView(graphObject);
        }
        populateGraphObjectView(result, graphObject);
        return result;
    }

    private View getActivityCircleView(View convertView, ViewGroup parent) {
        View result = convertView;
        if (result == null) {
            result = this.inflater.inflate(R.layout.com_facebook_picker_activity_circle_row, (ViewGroup) null);
        }
        ((ProgressBar) result.findViewById(R.id.com_facebook_picker_row_activity_circle)).setVisibility(0);
        return result;
    }

    /* access modifiers changed from: protected */
    public int getGraphObjectRowLayoutId(T t) {
        return R.layout.com_facebook_picker_list_row;
    }

    /* access modifiers changed from: protected */
    public int getDefaultPicture() {
        return R.drawable.com_facebook_profile_default_icon;
    }

    /* access modifiers changed from: protected */
    public View createGraphObjectView(T graphObject) {
        View result = this.inflater.inflate(getGraphObjectRowLayoutId(graphObject), (ViewGroup) null);
        ViewStub checkboxStub = (ViewStub) result.findViewById(R.id.com_facebook_picker_checkbox_stub);
        if (checkboxStub != null) {
            if (!getShowCheckbox()) {
                checkboxStub.setVisibility(8);
            } else {
                updateCheckboxState((CheckBox) checkboxStub.inflate(), false);
            }
        }
        ViewStub profilePicStub = (ViewStub) result.findViewById(R.id.com_facebook_picker_profile_pic_stub);
        if (!getShowPicture()) {
            profilePicStub.setVisibility(8);
        } else {
            ((ImageView) profilePicStub.inflate()).setVisibility(0);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public void populateGraphObjectView(View view, T graphObject) {
        URI pictureURI;
        String id = getIdOfGraphObject(graphObject);
        view.setTag(id);
        CharSequence title = getTitleOfGraphObject(graphObject);
        TextView titleView = (TextView) view.findViewById(R.id.com_facebook_picker_title);
        if (titleView != null) {
            titleView.setText(title, TextView.BufferType.SPANNABLE);
        }
        CharSequence subtitle = getSubTitleOfGraphObject(graphObject);
        TextView subtitleView = (TextView) view.findViewById(R.id.picker_subtitle);
        if (subtitleView != null) {
            if (subtitle != null) {
                subtitleView.setText(subtitle, TextView.BufferType.SPANNABLE);
                subtitleView.setVisibility(0);
            } else {
                subtitleView.setVisibility(8);
            }
        }
        if (getShowCheckbox()) {
            updateCheckboxState((CheckBox) view.findViewById(R.id.com_facebook_picker_checkbox), isGraphObjectSelected(id));
        }
        if (getShowPicture() && (pictureURI = getPictureUriOfGraphObject(graphObject)) != null) {
            ImageView profilePic = (ImageView) view.findViewById(R.id.com_facebook_picker_image);
            if (this.prefetchedPictureCache.containsKey(id)) {
                ImageResponse response = this.prefetchedPictureCache.get(id);
                profilePic.setImageBitmap(response.getBitmap());
                profilePic.setTag(response.getRequest().getImageUri());
                return;
            }
            downloadProfilePicture(id, pictureURI, profilePic);
        }
    }

    /* access modifiers changed from: package-private */
    public String getIdOfGraphObject(T graphObject) {
        if (graphObject.asMap().containsKey(ID)) {
            Object obj = graphObject.getProperty(ID);
            if (obj instanceof String) {
                return (String) obj;
            }
        }
        throw new FacebookException("Received an object without an ID.");
    }

    /* access modifiers changed from: package-private */
    public boolean filterIncludesItem(T graphObject) {
        return this.filter == null || this.filter.includeItem(graphObject);
    }

    /* access modifiers changed from: package-private */
    public Filter<T> getFilter() {
        return this.filter;
    }

    /* access modifiers changed from: package-private */
    public void setFilter(Filter<T> filter2) {
        this.filter = filter2;
    }

    /* access modifiers changed from: package-private */
    public boolean isGraphObjectSelected(String graphObjectId) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void updateCheckboxState(CheckBox checkBox, boolean graphObjectSelected) {
    }

    /* access modifiers changed from: package-private */
    public String getPictureFieldSpecifier() {
        ImageView picture = (ImageView) createGraphObjectView((GraphObject) null).findViewById(R.id.com_facebook_picker_image);
        if (picture == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = picture.getLayoutParams();
        return String.format(Locale.US, "picture.height(%d).width(%d)", new Object[]{Integer.valueOf(layoutParams.height), Integer.valueOf(layoutParams.width)});
    }

    private boolean shouldShowActivityCircleCell() {
        return this.cursor != null && this.cursor.areMoreObjectsAvailable() && this.dataNeededListener != null && !isEmpty();
    }

    private void rebuildSections() {
        boolean z = true;
        this.sectionKeys = new ArrayList();
        this.graphObjectsBySection = new HashMap();
        this.graphObjectsById = new HashMap();
        this.displaySections = false;
        if (this.cursor != null && this.cursor.getCount() != 0) {
            int objectsAdded = 0;
            this.cursor.moveToFirst();
            do {
                T graphObject = this.cursor.getGraphObject();
                if (filterIncludesItem(graphObject)) {
                    objectsAdded++;
                    String sectionKeyOfItem = getSectionKeyOfGraphObject(graphObject);
                    if (!this.graphObjectsBySection.containsKey(sectionKeyOfItem)) {
                        this.sectionKeys.add(sectionKeyOfItem);
                        this.graphObjectsBySection.put(sectionKeyOfItem, new ArrayList());
                    }
                    this.graphObjectsBySection.get(sectionKeyOfItem).add(graphObject);
                    this.graphObjectsById.put(getIdOfGraphObject(graphObject), graphObject);
                }
            } while (this.cursor.moveToNext());
            if (this.sortFields != null) {
                final Collator collator = Collator.getInstance();
                for (ArrayList<T> section : this.graphObjectsBySection.values()) {
                    Collections.sort(section, new Comparator<GraphObject>() {
                        public int compare(GraphObject a, GraphObject b) {
                            return GraphObjectAdapter.compareGraphObjects(a, b, GraphObjectAdapter.this.sortFields, collator);
                        }
                    });
                }
            }
            Collections.sort(this.sectionKeys, Collator.getInstance());
            if (this.sectionKeys.size() <= 1 || objectsAdded <= 1) {
                z = false;
            }
            this.displaySections = z;
        }
    }

    /* access modifiers changed from: package-private */
    public SectionAndItem<T> getSectionAndItem(int position) {
        if (this.sectionKeys.size() == 0) {
            return null;
        }
        String sectionKey = null;
        T graphObject = null;
        if (this.displaySections) {
            Iterator i$ = this.sectionKeys.iterator();
            while (true) {
                if (!i$.hasNext()) {
                    break;
                }
                String key = i$.next();
                int position2 = position - 1;
                if (position == 0) {
                    sectionKey = key;
                    int i = position2;
                    break;
                }
                List<T> section = this.graphObjectsBySection.get(key);
                if (position2 < section.size()) {
                    sectionKey = key;
                    graphObject = (GraphObject) section.get(position2);
                    int i2 = position2;
                    break;
                }
                position = position2 - section.size();
            }
        } else {
            sectionKey = this.sectionKeys.get(0);
            List<T> section2 = this.graphObjectsBySection.get(sectionKey);
            if (position >= 0 && position < section2.size()) {
                graphObject = (GraphObject) this.graphObjectsBySection.get(sectionKey).get(position);
            } else if ($assertionsDisabled || (this.dataNeededListener != null && this.cursor.areMoreObjectsAvailable())) {
                return new SectionAndItem<>((String) null, null);
            } else {
                throw new AssertionError();
            }
        }
        if (sectionKey != null) {
            return new SectionAndItem<>(sectionKey, graphObject);
        }
        throw new IndexOutOfBoundsException("position");
    }

    /* access modifiers changed from: package-private */
    public int getPosition(String sectionKey, T graphObject) {
        int position = 0;
        boolean found = false;
        Iterator i$ = this.sectionKeys.iterator();
        while (true) {
            if (!i$.hasNext()) {
                break;
            }
            String key = i$.next();
            if (this.displaySections) {
                position++;
            }
            if (key.equals(sectionKey)) {
                found = true;
                break;
            }
            position += this.graphObjectsBySection.get(key).size();
        }
        if (!found) {
            return -1;
        }
        if (graphObject == null) {
            return position - (this.displaySections ? 1 : 0);
        }
        Iterator i$2 = this.graphObjectsBySection.get(sectionKey).iterator();
        while (i$2.hasNext()) {
            if (GraphObject.Factory.hasSameId((GraphObject) i$2.next(), graphObject)) {
                return position;
            }
            position++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.sectionKeys.size() == 0;
    }

    public int getCount() {
        int count = 0;
        if (this.sectionKeys.size() == 0) {
            return 0;
        }
        if (this.displaySections) {
            count = this.sectionKeys.size();
        }
        for (ArrayList<T> section : this.graphObjectsBySection.values()) {
            count += section.size();
        }
        if (shouldShowActivityCircleCell()) {
            return count + 1;
        }
        return count;
    }

    public boolean areAllItemsEnabled() {
        return this.displaySections;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEnabled(int position) {
        return getSectionAndItem(position).getType() == SectionAndItem.Type.GRAPH_OBJECT;
    }

    public Object getItem(int position) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        if (sectionAndItem.getType() == SectionAndItem.Type.GRAPH_OBJECT) {
            return sectionAndItem.graphObject;
        }
        return null;
    }

    public long getItemId(int position) {
        String id;
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        if (!(sectionAndItem == null || sectionAndItem.graphObject == null || (id = getIdOfGraphObject(sectionAndItem.graphObject)) == null)) {
            try {
                return Long.parseLong(id);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public int getItemViewType(int position) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        switch (sectionAndItem.getType()) {
            case SECTION_HEADER:
                return 0;
            case GRAPH_OBJECT:
                return 1;
            case ACTIVITY_CIRCLE:
                return 2;
            default:
                throw new FacebookException("Unexpected type of section and item.");
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        switch (sectionAndItem.getType()) {
            case SECTION_HEADER:
                return getSectionHeaderView(sectionAndItem.sectionKey, convertView, parent);
            case GRAPH_OBJECT:
                return getGraphObjectView(sectionAndItem.graphObject, convertView, parent);
            case ACTIVITY_CIRCLE:
                if ($assertionsDisabled || (this.cursor.areMoreObjectsAvailable() && this.dataNeededListener != null)) {
                    this.dataNeededListener.onDataNeeded();
                    return getActivityCircleView(convertView, parent);
                }
                throw new AssertionError();
            default:
                throw new FacebookException("Unexpected type of section and item.");
        }
    }

    public Object[] getSections() {
        if (this.displaySections) {
            return this.sectionKeys.toArray();
        }
        return new Object[0];
    }

    public int getPositionForSection(int section) {
        int section2;
        if (!this.displaySections || (section2 = Math.max(0, Math.min(section, this.sectionKeys.size() - 1))) >= this.sectionKeys.size()) {
            return 0;
        }
        return getPosition(this.sectionKeys.get(section2), (GraphObject) null);
    }

    public int getSectionForPosition(int position) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        if (sectionAndItem == null || sectionAndItem.getType() == SectionAndItem.Type.ACTIVITY_CIRCLE) {
            return 0;
        }
        return Math.max(0, Math.min(this.sectionKeys.indexOf(sectionAndItem.sectionKey), this.sectionKeys.size() - 1));
    }

    public List<T> getGraphObjectsById(Collection<String> ids) {
        Set<String> idSet = new HashSet<>();
        idSet.addAll(ids);
        ArrayList<T> result = new ArrayList<>(idSet.size());
        for (String id : idSet) {
            T graphObject = (GraphObject) this.graphObjectsById.get(id);
            if (graphObject != null) {
                result.add(graphObject);
            }
        }
        return result;
    }

    private void downloadProfilePicture(final String profileId, URI pictureURI, final ImageView imageView) {
        if (pictureURI != null) {
            boolean prefetching = imageView == null;
            if (prefetching || !pictureURI.equals(imageView.getTag())) {
                if (!prefetching) {
                    imageView.setTag(profileId);
                    imageView.setImageResource(getDefaultPicture());
                }
                ImageRequest newRequest = new ImageRequest.Builder(this.context.getApplicationContext(), pictureURI).setCallerTag(this).setCallback(new ImageRequest.Callback() {
                    public void onCompleted(ImageResponse response) {
                        GraphObjectAdapter.this.processImageResponse(response, profileId, imageView);
                    }
                }).build();
                this.pendingRequests.put(profileId, newRequest);
                ImageDownloader.downloadAsync(newRequest);
            }
        }
    }

    private void callOnErrorListener(Exception exception) {
        if (this.onErrorListener != null) {
            if (!(exception instanceof FacebookException)) {
                exception = new FacebookException((Throwable) exception);
            }
            this.onErrorListener.onError(this, (FacebookException) exception);
        }
    }

    /* access modifiers changed from: private */
    public void processImageResponse(ImageResponse response, String graphObjectId, ImageView imageView) {
        this.pendingRequests.remove(graphObjectId);
        if (response.getError() != null) {
            callOnErrorListener(response.getError());
        }
        if (imageView == null) {
            if (response.getBitmap() != null) {
                if (this.prefetchedPictureCache.size() >= 20) {
                    this.prefetchedPictureCache.remove(this.prefetchedProfilePictureIds.remove(0));
                }
                this.prefetchedPictureCache.put(graphObjectId, response);
            }
        } else if (graphObjectId.equals(imageView.getTag())) {
            Exception error = response.getError();
            Bitmap bitmap = response.getBitmap();
            if (error == null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
                imageView.setTag(response.getRequest().getImageUri());
            }
        }
    }

    /* access modifiers changed from: private */
    public static int compareGraphObjects(GraphObject a, GraphObject b, Collection<String> sortFields2, Collator collator) {
        for (String sortField : sortFields2) {
            String sa = (String) a.getProperty(sortField);
            String sb = (String) b.getProperty(sortField);
            if (sa != null && sb != null) {
                int result = collator.compare(sa, sb);
                if (result != 0) {
                    return result;
                }
            } else if (sa != null || sb != null) {
                return sa == null ? -1 : 1;
            }
        }
        return 0;
    }
}
