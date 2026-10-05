import React from 'react';

export default function HealthCard({ healthStatus, loading, onRefresh }) {
  return (
    <div id="health" className="health-monitor-card glass-card">
      <div className="health-header">
        <h3>
          <span>⚙️</span> Backend Health Check API
        </h3>
        <span className="endpoint-badge">GET /api/health</span>
        <button className="recheck-btn" onClick={onRefresh} disabled={loading}>
          {loading ? 'Testing...' : '🔄 Recheck Connection'}
        </button>
      </div>

      <p style={{ color: 'var(--text-muted)', fontSize: '0.95rem', marginBottom: '1rem' }}>
        Live response from Spring Boot backend (running on <code>http://localhost:8081</code>):
      </p>

      <div className="json-preview">
        {loading ? (
          <span style={{ color: 'var(--text-muted)' }}>Connecting to backend server...</span>
        ) : healthStatus?.success ? (
          <pre>
{`{
  "`}<span className="json-key">status</span>{`": "`}<span className="json-string">{healthStatus.data?.status}</span>{`",
  "`}<span className="json-key">message</span>{`": "`}<span className="json-string">{healthStatus.data?.message}</span>{`"
}`}
          </pre>
        ) : (
          <div className="json-error">
            <p>❌ Connection Error:</p>
            <p style={{ fontSize: '0.875rem', marginTop: '0.25rem' }}>{healthStatus?.error}</p>
            <p style={{ fontSize: '0.8rem', color: 'var(--text-dim)', marginTop: '0.5rem' }}>
              Ensure Spring Boot backend is running via <code>mvn spring-boot:run</code> in the <code>backend/</code> folder.
            </p>
          </div>
        )}
      </div>
    </div>
  );
}
